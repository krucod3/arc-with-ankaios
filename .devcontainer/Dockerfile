FROM mcr.microsoft.com/devcontainers/java:21

RUN curl -sfL https://github.com/eclipse-ankaios/ankaios/releases/latest/download/install.sh | bash -

# All the packages we need
RUN export DEBIAN_FRONTEND=noninteractive \
    && apt update \
    && apt -y install \
    podman \
    # Cleanup
    && rm -rf /var/lib/apt/lists/*
