# Variables to be used
resourceGroupName="az-nashtech-resource-group"
resourceLocation="EastUS"
clusterName="ntdemocluster"

#Scripts

#Login to Azure in Local
az login

#Create Resource Group
az group create --name $resourceGroupName --location $resourceLocation

#Create an AKS Cluster with default setting
az aks create --resource-group $resourceGroupName --name $clusterName

#Get the Kubernetes Configuration to run further commands
az aks get-credentials --resource-group $resourceGroupName --name $clusterName

#Run this command to install CRDS
kubectl create -f https://download.elastic.co/downloads/eck/2.9.0/crds.yaml

# Download the required repo for elastic
kubectl apply -f https://download.elastic.co/downloads/eck/2.9.0/operator.yaml

#Apply the deployment File
kubectl apply -f elastic-deployment.yaml

# Once Pod is up take the Cluster Ip and add it into the elastic-svc.yaml file
kubectl apply -f elastic-svc.yaml





