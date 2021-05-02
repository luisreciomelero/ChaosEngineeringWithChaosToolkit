#!/bin/bash

echo "Habilitamos los experimentos periodicos internos al cluster"

cd "../../kubernetes-stack/yamls-post-chaos/chaos-automated"

kubectl apply -f "."