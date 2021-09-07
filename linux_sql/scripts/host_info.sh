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

#save hostname to a variable
hostname=$(hostname -f)

#save number of CPU to a variable
lscpu_out=`lscpu`
#note: `xargs` is a trick to remove leading and trailing white spaces
cpu_number=$(echo "$lscpu_out"  | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)

cpu_architecture=$(echo "$lscpu_out" | egrep "^Architecture:" | awk '{print $2}' | xargs)

cpu_model=$(echo "$lscpu_out" | egrep -w "^Model name:" | awk '{print $3 $4 $5 $6 $7}' | xargs)

cpu_mhz=$(echo "$lscpu_out" | egrep "^CPU MHz:" | awk '{print $3}' | xargs)

L2_cache=$(echo "$lscpu_out" | egrep "^L2 cache:" | awk '{print $3}' | xargs)

total_mem=$(cat /proc/meminfo | egrep "^MemTotal:" | awk '{print $2}' | xargs)
timestamp=$(date +"%Y-%m-%d %T")

insert_statement="INSERT INTO host_info(hostname,cpu_number,cpu_architecture,cpu_model,cpu_mhz,L2_cache,Total_mem,timestamp) VALUES ( '$hostname', '$cpu_number', '$cpu_architecture', '$cpu_model', '$cpu_mhz', '$L2_cache', '$total_mem', '$timestamp')"

export PGPASSWORD=$psql_password
psql -h "$psql_host" -p "$psql_port" -d "$db_name" -U "$psql_user" -c "$insert_statement"
exit 0