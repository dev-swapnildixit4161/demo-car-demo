#!/bin/sh

GKE_CLUSTER="$1"
REGION="$2"
# Authenticate axon-server-gke
gcloud container clusters get-credentials "$GKE_CLUSTER" --region "$REGION"

#Run this command to install CRDS
kubectl create -f https://download.elastic.co/downloads/eck/2.9.0/crds.yaml

# Download the required repo for elastic
kubectl apply -f https://download.elastic.co/downloads/eck/2.9.0/operator.yaml

#Apply the deployment File
kubectl apply -f elastic-deployment.yaml