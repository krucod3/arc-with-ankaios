import java.io.*

function(
    name = "get_ankaios_state",
    description = "Returns the current state of the Ankaios cluster as JSON. The desiredState describes the target state of the Ankaios cluster. The workloadStates field contains the current state of the workloads running in the Ankaios cluster. The agents field specifies the agents that are running in the Ankaios cluster including some of their attributes. The desiredState field contains the desired state of the Ankaios cluster.",
    params = types(string("arg", "The address of the Ankaios server."))
) { (arg) ->

    var serverUrl = "http://127.0.0.1:25551"
    if (arg != null && arg.toString().isNotEmpty()) {
        serverUrl = arg.toString()
    }

    val process = ProcessBuilder("bash", "-c", "ank -k -s $serverUrl get state -o json").start()
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
