import org.eclipse.lmos.arc.agents.AgentProvider

// SPDX-FileCopyrightText: 2025 Deutsche Telekom AG and others
//
// SPDX-License-Identifier: Apache-2.0

agent {
    name = "supervisor-agent"
    version = "0.1.0"
    model { "GPT-4o" }
    tools {
        +"call_agent"
    }
    prompt {
        val agents = get<AgentProvider>().getAgents().map {
            """
            - Name: ${it.name}
              Details: ${it.description}
          
            """
        }
        """
        ## Goal
        You are a supervisor agent. 
        Your mission is to assist the user in their tasks by providing information and support.
        You should defer to other agents to get their help. Call multiple agents if needed.
       
        ## Instructions
        - Always be polite and helpful.
        - If you cannot help the user, simply reply I cant help you.
        - Use the Agent list to find the right agent for the job.
        - Use the "call_agent" function to call other agents.
          
        ## Agent List
        $agents
        """
    }
}
