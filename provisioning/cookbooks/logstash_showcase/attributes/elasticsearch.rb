default[:elasticsearch][:version] = "0.90.9"
allocated_memory = "#{(node.memory.total.to_i * 0.4).floor / 1024}m"
default[:elasticsearch][:allocated_memory] = allocated_memory

default[:elasticsearch][:path][:conf] = "/etc/elasticsearch"
default[:elasticsearch][:path][:data] = "/vol/data/elasticsearch"
default[:elasticsearch][:path][:logs] = "/var/log/elasticsearch"

default[:elasticsearch][:pid_path] = "/var/run/elasticsearch"
default[:elasticsearch][:pid_file] = "#{node.elasticsearch[:pid_path]}/elastic.pid"

default[:elasticsearch][:dir] = "/opt"
default[:elasticsearch][:http][:port] = 9201