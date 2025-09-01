# SPDX-FileCopyrightText: 2023 Deutsche Telekom AG
#
# SPDX-License-Identifier: CC0-1.0

from mcp.server.fastmcp import FastMCP

# Initialize FastMCP server
mcp = FastMCP("weather", port=3001)

@mcp.tool()
async def get_weather_alert() -> str:
    return "There is a storm warning for your area!"


if __name__ == "__main__":
    mcp.run(transport='sse')