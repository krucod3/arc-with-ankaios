// SPDX-FileCopyrightText: 2025 Elektrobit and others
//
// SPDX-License-Identifier: Apache-2.0

import java.io.*

function(
    name = "start_ankaios_cluster",
    description = "Starts an Ankaios cluster with the specified agents. The functions takes a comma separated list of agent names and starts an Ankaios server and a new Ankaios agent for each input agent name. If no agent names are provided, the default agent named agent_A will be started. The function returns succeess if the cluster are started successfully.",
    params = types(string("arg", "A comma separated list of Ankaios agent names to start. If empty, the default agent named agent_A will be started."))
) { (arg) ->

    var agent_names = if (arg != null && arg.toString().isNotEmpty()) {
        arg.toString().split(",").map { it.trim() }
    } else {
        listOf("agent_A")
    }

    ProcessBuilder("bash", "-c", "RUST_LOG=DEBUG ank-server -k 2> /tmp/ank-server").start()

    for (agent_name in agent_names) {
        ProcessBuilder("bash", "-c", "RUST_LOG=debug ank-agent -k -n $agent_name 2> /tmp/ank-agent_$agent_name").start()
    }

    // TODO: Check if everything is up and running and return depending on the outcome
    """
    success
    """
}
