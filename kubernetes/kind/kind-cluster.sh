#!/bin/sh

# Resolve parent script directory
parent_path=$(cd "$(dirname "$0")" && pwd -P)
cd "$parent_path" || exit 1

create() {
    printf "Initializing Kubernetes cluster...\n"
    kind create cluster --config kind-config.yml

    printf "\n-----------------------------------------------------\n"
    printf "Installing NGINX Ingress...\n"
    kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml

    printf "\n-----------------------------------------------------\n"
    printf "Waiting for NGINX Ingress to be ready...\n"
    sleep 10

    kubectl wait --namespace ingress-nginx \
      --for=condition=ready pod \
      --selector=app.kubernetes.io/component=controller \
      --timeout=250s

    printf "\nHappy Sailing!\n"
}

destroy() {
    printf "Destroying Kubernetes cluster...\n"
    kind delete cluster --name bookstore
}

help() {
    printf "Usage: %s create|destroy\n" "$0"
}

# Default action
action="help"

# If user passed an argument, use it
if [ "$#" -gt 0 ]; then
    action="$1"
fi

case "$action" in
    create) create ;;
    destroy) destroy ;;
    *) help ;;
esac
