#!/bin/bash
set -e

# Kill all ank-server and ank-agent processes
pkill -f ank-server
pkill -f ank-agent

# Delete all podman containers
podman rm -f $(podman ps -aq) 2>/dev/null

echo "Ankaios is stopped and all podman containers are deleted."