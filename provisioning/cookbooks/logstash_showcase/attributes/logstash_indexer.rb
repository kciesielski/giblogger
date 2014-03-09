default[:logstash][:patterns][:base]["MULTILINE_GREEDYDATA"] = '(.|\n)*'
default[:logstash][:install_rabbitmq] = false
default[:logstash][:server][:base_config] = "logstash-server.conf.erb"
default[:logstash][:server][:base_config_cookbook] = "logstash_showcase"
default[:logstash][:server][:enable_embedded_es] = false