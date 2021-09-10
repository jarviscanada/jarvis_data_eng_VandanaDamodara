
# Queries to find some basic information from the table of host _usage and host_info

# Query 1:Group hosts by CPU number and sort by their memory size in descending order

SELECT cpu_number,id,total_mem  FROM host_info  ORDER BY cpu_number,total_mem DESC ;

#output cpu_number | id | total_mem
#        ------------+----+-----------
#                 2 |  1 |   8005740
#                 2 |  5 |   5005760
#                 3 |  2 |   8005760
#                 3 |  4 |   700576#
#                 5 |  3 |   9005760
        (5 rows)

#Query 2:Average used memory in percentage over 5 mins interval for each host

CREATE FUNCTION round5(ts timestamp) RETURNS timestamp AS
$$
BEGIN
    RETURN date_trunc('hour', ts) + date_part('minute', ts):: int / 5 * interval '5 min';
END;
$$
    LANGUAGE PLPGSQL;

SELECT host_info.id,host_info.hostname,round5(host_usage.timestamp) as rounded_time,
AVG((host_info.total_mem-host_usage.memory_free)*100/host_info.total_mem) as avrg
FROM host_usage INNER JOIN host_info ON host_info.id = host_usage.host_id
GROUP BY host_info.id,rounded_time
ORDER BY rounded_time;

#Query 3 : query to check if server is failed

SELECT host_id ,round5(timestamp) as ts,count(round5(timestamp)) as num_data_points
 FROM host_usage GROUP BY host_id,ts HAVING count(round5(timestamp))<3 ORDER BY host_id;

#output  host_id |         ts          | num_data_points
#        ---------+---------------------+-----------------
#               1 | 2021-09-06 23:25:00 |               1
#        (1 row)
