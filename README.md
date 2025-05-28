<!--
SPDX-FileCopyrightText: 2023 Deutsche Telekom AG, Elektrobit and others

SPDX-License-Identifier: CC0-1.0    
-->
# Welcome to the Ankaios demo with ARC agents

This project showcases how AI agents can help demonstrate the capabilities of Ankaios.
The repo is a fork from LMOS ARC and includes a custom devcontainer 

## How to run

#### 0. Open the repo in the provided devcontainer, e.g., with VS Code

#### 1. Add language model configuration

Add an OpenAI API-KEY to `config/application.yml` or as the environment variable, `OPENAI_API_KEY`.

```
arc:
  ai:
    clients:
      - id: GPT-4o
        model-name: GPT-4o
        api-key: ${OPENAI_API_KEY}
        client: openai
      - id: llama3.3
        modelName: llama3.3
        client: ollama
```

Alternatively, you can run an LLM locally with the `ollama` client, see https://ollama.com/,
and change the model in the agent `assistant.agent.kts` file accordingly.

```kts
agent {
    name = "assistant-agent"
    model = { "llama3.3" }
    ...
}
```

#### 2. Start the Application

Start the Demo Application like a normal Spring Boot application.
This requires the port 8080 to be available.

```bash
  ./gradlew bootRun
```

#### 3. Access the Agent

You can chat with the Arc Agents using the [Arc View](https://github.com/eclipse-lmos/arc-view).

Simply open https://eclipse.dev/lmos/chat/index.html?agentUrl=http://localhost:8080#/chat in your browser.


#### 4. Get to know Ankaios

Ask the agent in the chat a question about Ankaios, like:

> What is Ankaios?

Think of your own questions and explore what is possible

> [!NOTE]
> If you want to start over and need to clean up the Ankaios cluster just run the `clean-up-ankaios.sh` script from the root folder.
> Also be aware that the agent keeps track of the conversations and includes the knowledge in its answers. If you cleanup the cluster, you should start a new conversation or refresh the page to avoid discrepancies between the state of the cluster and the one of the agent.

## Code of Conduct

This project has adopted the [Contributor Covenant](https://www.contributor-covenant.org/) in version 2.1 as our code of conduct. Please see the details in our [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md). All contributors must abide by the code of conduct.

By participating in this project, you agree to abide by its [Code of Conduct](./CODE_OF_CONDUCT.md) at all times.

## Licensing

This project follows the [REUSE standard for software licensing](https://reuse.software/).    
Each file contains copyright and license information, and license texts can be found in the [./LICENSES](./LICENSES) folder. For more information visit https://reuse.software/.    
You can find a guide for developers at https://telekom.github.io/reuse-template/.   
