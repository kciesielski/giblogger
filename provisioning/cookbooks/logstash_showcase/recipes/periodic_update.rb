# Periodically update apt
# This is especially important when provisioning a new node
# without running apt-get update we'd end up fetching packages not existing anymore
# due to the fact that they were replaced in the "update" or "security" channels
execute "apt-get-update-periodic" do
  command "apt-get update"
  ignore_failure true
  only_if do
    !File.exists?('/var/lib/apt/periodic/update-success-stamp' ||
                      File.mtime('/var/lib/apt/periodic/update-success-stamp') < Time.now - 86400)
  end
end
