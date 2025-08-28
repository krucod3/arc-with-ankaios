// SPDX-FileCopyrightText: 2025 Deutsche Telekom AG and others
//
// SPDX-License-Identifier: Apache-2.0

package org.eclipse.lmos.arc.app

import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader


/**
 * Executes a string as a command and returns the return code.
 */
fun String.exec(workingDir: File? = null): Pair<CommandResult, Sequence<String>> {
    ProcessBuilder().apply {
        workingDir?.let { directory(it) }
        command(split(" "))
        redirectErrorStream(true)
        val process = start()
        val result = process.readStream()
        val code = process.waitFor()
        return CommandResult(code) to result
    }
}

class CommandResult(val code: Int) {

    val isFailed = code != 0
    val isSuccess = !isFailed

    fun ifFailed(block: () -> Unit) {
        if (isFailed) block()
    }
}


private fun Process.readStream() = sequence<String> {
    val reader = BufferedReader(InputStreamReader(inputStream))
    try {
        var line: String?
        while (true) {
            line = reader.readLine()
            if (line == null) {
                break
            }
            yield(line)
        }
    } finally {
        reader.close()
    }
}
