#!/bin/bash

# Kill all ank-server and ank-agent processes
pkill -f ank-server
pkill -f ank-agent

# Delete all podman containers
podman rm -af 

echo "Ankaios is stopped and all podman containers are deleted."
