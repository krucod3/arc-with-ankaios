<!--
SPDX-FileCopyrightText: 2023 Deutsche Telekom AG

SPDX-License-Identifier: CC0-1.0    
-->
# MCP Test Server

This is an example MCP server implementation for testing purposes.
It shows how the Arc Framework can integrate tools host on an MCP server.

To start the server, run:

```bash

pip install -r requirements.txt
python3 my_mcp_server.py

```

Configure the Arc Framework to connect to the server by setting the tools url in the src/main/resources/application.yml:

```yml
arc:
  mcp:
   tools:
     urls: http://localhost:3001
```

The agent can then access the hosted tools by using the tool name, e.g. `mcp_echo`.

example:

```kotlin
agent {
    name = "weather-agent"
    prompt { """ Prompt """ }
    tools {
        +"get_weather_alert" // coming from the MCP server
    }
}
```
