kubectl --kubeconfig="./infrastructure-as-code/config/kubeconfig.yaml" apply -f ./infrastructure-as-code/kubernetes/deployment.yaml
kubectl --kubeconfig="./infrastructure-as-code/config/kubeconfig.yaml" apply -f ./infrastructure-as-code/kubernetes/load-balancer.yaml
kubectl --kubeconfig="./infrastructure-as-code/config/kubeconfig.yaml" get services