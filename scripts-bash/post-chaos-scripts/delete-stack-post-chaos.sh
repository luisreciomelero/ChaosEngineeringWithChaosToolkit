#!/bin/bash

cd "../../kubernetes-stack/yamls-post-chaos"

cd "istio"

kubectl delete -f "ingress-gateway.yaml"


cd "../postgres-db"


kubectl delete -f "postgres-dep.yaml"

cd "../kafka-app"

kubectl delete -f "."

cd "../mg-app"

kubectl delete -f "mg-deploy.yaml"

cd "../crud-app"

kubectl delete -f "."

cd "../mailer-app"


kubectl delete -f "config-mailer.yaml,ms-mailer.yaml"

cd "../sendersms-app"

kubectl delete -f "."

cd "../virtualservices"

kubectl delete -f "."

cd "../horizontalPodAutoScalers"

kubectl delete -f "metrics-components.yaml,mg-hpa.yaml,ms-read-hpa.yaml"

cd "../chaos-automated"

kubectl delete -f "."

cd "../../../scripts-bash/post-chaos-scripts/"