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

You can chat with the Arc Agents using the [Arc View](http://github.com/eclipse-lmos/arc-view).

Simply open http://localhost:8080/chat/index.html#/chat in your browser.

> [!NOTE]
> You might need to forward the port 8080 from the devcontainer to be able to access the assistant.

#### 4. Get to know Ankaios

Ask the agent in the chat a question about Ankaios, like:

> What is Ankaios?

Think of your own questions and explore what is possible

> [!NOTE]
> If you want to start over and need to clean up the Ankaios cluster just run the `clean-up-ankaios.sh` script from the root folder.
> Also be aware that the agent keeps track of the conversations and includes the knowledge in its answers. If you cleanup the cluster, you should start a new conversation or refresh the page to avoid discrepancies between the state of the cluster and the one of the agent.

#### 4.1 Add new Agents (assistants)

New agents can be added to the `agents` folder located at the root of the project.
The folder contains a default agent `assistant-agent` that can be used as a template.


#### 5. Tracing

The Arc Framework supports tracing with Micrometer Tracing. 
See [Arc Tracing](https://eclipse.dev/lmos/docs/arc/tracing/) for more information.

By default, tracing is disabled in the project. To enable it, update your application.yaml as follows:
```yaml
management:
  tracing:
    enabled: true
```

To use tracing with [Arize Phoenix](https://phoenix.arize.com/), start the Phoenix server using Docker:
```shell
docker run -p 6006:6006 -p 4317:4317 -i -t arizephoenix/phoenix:latest
```

Open the Phoenix UI in your browser at [http://localhost:6006/projects](http://localhost:9411/) to watch the traces of your Arc Agents in real time.


## In Production

When deploying the application in production, you may not want to use kotlin scripts.

For this purpose, we have the Arc Gradle Plugin that will transpile the kotlin scripts into kotlin classes.

Simply add the following to your `build.gradle.kts` file:

```kts
plugins {
  id("org.eclipse.lmos.arc.gradle.plugin") version "0.124.0"
}
```

The transpiled classes will be generated in the `/build/arc/kotlin` folder.

For these classes to be loaded, you will also need to disable the kotlin scripts in your `application.yml` file:

```yml
arc:
  scripts:
    enabled: false
```

This will also prevent any kotlin scripts from being loaded.

## Code of Conduct

This project has adopted the [Contributor Covenant](https://www.contributor-covenant.org/) in version 2.1 as our code of conduct. Please see the details in our [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md). All contributors must abide by the code of conduct.

By participating in this project, you agree to abide by its [Code of Conduct](./CODE_OF_CONDUCT.md) at all times.

## Licensing

This project follows the [REUSE standard for software licensing](https://reuse.software/).    
Each file contains copyright and license information, and license texts can be found in the [./LICENSES](./LICENSES) folder. For more information visit https://reuse.software/.    
You can find a guide for developers at https://telekom.github.io/reuse-template/.   
