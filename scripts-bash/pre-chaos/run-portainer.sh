#!/bin/bash

helm install --create-namespace -n portainer portainer portainer/portainer

export NODE_PORT=$(kubectl get --namespace portainer -o jsonpath="{.spec.ports[0].nodePort}" services portainer)
export NODE_IP=$(kubectl get nodes --namespace portainer -o jsonpath="{.items[0].status.addresses[0].address}")
echo http://$NODE_IP:$NODE_PORT