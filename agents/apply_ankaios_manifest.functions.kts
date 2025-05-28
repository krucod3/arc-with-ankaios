// SPDX-FileCopyrightText: 2025 Elektrobit and others
//
// SPDX-License-Identifier: Apache-2.0

import java.io.*

function(
    name = "apply_ankaios_manifest",
    description = "Applies an Ankaios manifest (a yaml file) which could update and start one or more workloads. The functions takes manifest as input and applies it to the Ankaios cluster. An Ankaios manifest is the same as an Ankaios startup configuration. The function returns the outcome of the operation.",
    params = types(string("arg", "A string containing a yaml file with an Ankaios manifest (which is the same as an Ankaios startup configuration). If empty, nothing is done."))
) { (arg) ->

    if (arg == null || arg.toString().isEmpty()) {
        "Nothing to apply."
    } else {

        var manifest = arg.toString()

        // val process = ProcessBuilder("bash", "-c", "echo \'$manifest\' | ank -k apply -").start() // this does not work it seems like 
        val process = ProcessBuilder("bash", "-c", "echo \'$manifest\' | ank -k apply -").start()
        
        val output = BufferedReader(InputStreamReader(process.inputStream)).use { it.readText() }
        val error = BufferedReader(InputStreamReader(process.errorStream)).use { it.readText() }

        // TODO: we could also react on certain states and stop the waiting, e.g., if the retry mechanism is started in Ankaios
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
