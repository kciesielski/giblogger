default[:logstash][:patterns][:base]["MULTILINE_GREEDYDATA"] = '(.|\n)*'
default[:logstash][:agent][:base_config] = "logstash-agent.conf.erb"
default[:logstash][:agent][:base_config_cookbook] = "logstash_showcase"
default[:logstash_agent][:app_log_path] = "/var/log/gibberish.log"