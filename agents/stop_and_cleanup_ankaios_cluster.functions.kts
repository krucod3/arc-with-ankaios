// SPDX-FileCopyrightText: 2025 Elektrobit and others
//
// SPDX-License-Identifier: Apache-2.0

import java.io.*

function(
    name = "stop_and_cleanup_ankaios_cluster",
    description = "Stops a running Ankaios cluster and deletes all workloads.",
    params = types(string("arg", "A string instructing what to clean. If 'delete_workloads' is provided, all workloads are deleted. If empty, only the Ankaios cluster is stopped."),)
) { (arg) ->

    if (arg != null && arg.toString().contains("delete_workloads")) {
        ProcessBuilder("bash", "-c", "podman stop --all").start()
        ProcessBuilder("bash", "-c", "podman rm --all").start()
    } 
    ProcessBuilder("bash", "-c", "pkill ank-server").start()
    ProcessBuilder("bash", "-c", "pkill ank-agent").start()
    
    // TODO: Check if everything is stopped and return depending on the outcome
    """
    success
    """
}
