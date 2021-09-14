#!/bin/bash

psql_host=$1
psql_port=$2
db_name=$3
psql_user=$4
psql_password=$5

# check to make sure correct number of arguments were given
if [ "$#" -ne 5 ]; then
    echo "Incorrect number of arguments given"
    exit 1
fi

host_name=$(hostname -f)
timestamp=$(date +"%Y-%m-%d %T")
memory_free=$(cat /proc/meminfo | egrep "^MemFree:" | awk '{print $2}' | xargs)
cpu_kernel=$(vmstat | egrep -v 'cpu|sy' | awk '{ print $14 }' | xargs)
cpu_idle=$(vmstat | egrep -v 'cpu|id' | awk '{ print $15 }' | xargs)
disk_io=$(vmstat | egrep -v 'io|bi' | awk '{ print $9+$10 }' | xargs)
disk_available=$(df -BM / | egrep "^/dev/sda2" | awk '{print $4}' | sed 's/.$//' | xargs)


insert_stmnt="INSERT INTO host_usage(host_id,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available,timestamp) VALUES ((SELECT id FROM host_info WHERE hostname='$host_name'),'$memory_free','$cpu_idle','$cpu_kernel','$disk_io','$disk_available','$timestamp')"

export PGPASSWORD=$psql_password
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_stmnt"
exit $?