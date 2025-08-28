// SPDX-FileCopyrightText: 2025 Deutsche Telekom AG and others
//
// SPDX-License-Identifier: Apache-2.0
import org.eclipse.lmos.arc.app.*

function(
    name = "system_call",
    description = "Calls a process on the system.",
    params = types(
        string("command", "The command to execute.")
    )
) { (command) ->
    command.toString().exec().second.joinToString("\n")
}