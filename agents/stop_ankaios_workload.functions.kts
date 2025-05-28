// SPDX-FileCopyrightText: 2025 Elektrobit and others
//
// SPDX-License-Identifier: Apache-2.0

import java.io.*

function(
    name = "stop_ankaios_workload",
    description = "Stops Ankaios workloads. The function takes a comma separated list of workload names and stops them. The function returns the outcome of the operation.",
    params = types(string("arg", "A comma separated list of workload names to stop. If empty, nothing is stopped."))
) { (arg) ->

    if (arg == null || arg.toString().isEmpty()) {
        "No workloads to stop."
    } else {

        var workload_names = arg.toString().split(",").map { it.trim() }.joinToString(" ")

        val process = ProcessBuilder("bash", "-c", "ank -k delete workloads $workload_names").start()
        
        val output = BufferedReader(InputStreamReader(process.inputStream)).use { it.readText() }
        val error = BufferedReader(InputStreamReader(process.errorStream)).use { it.readText() }

        if (process.waitFor() == 0) {
            output
        } else {
            """
            {
                "error": "$error"
            }
            """
        }
    }
}
