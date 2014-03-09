directory "/etc/nginx" do
  owner "root"
  group "root"
  mode "0755"
  action :create
end

directory "/etc/nginx/ssl" do
  owner "root"
  group "root"
  mode "0755"
  action :create
end

# Username: logstash, pass: qwerty123
htpasswd_content = 'logstash:$apr1$N0DM3mYc$9r1hj4Hb2y4JZjxd5x5f61'
file "/etc/nginx/htpasswd-logstash" do
  content htpasswd_content
  owner "root"
  group "root"
  mode 0755
end

cookbook_file "/etc/nginx/ssl/server.crt" do
  source "server.crt"
  owner "root"
  group "root"
  mode 0755
end

cookbook_file "/etc/nginx/ssl/server.key" do
  source "server.key"
  owner "root"
  group "root"
  mode 0755
end

include_recipe "kibana"