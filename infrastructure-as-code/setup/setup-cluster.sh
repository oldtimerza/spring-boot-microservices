kubectl --kubeconfig="../config/kubeconfig.yaml" apply -f ../kubernetes/deployment.yaml
kubectl --kubeconfig="../config/kubeconfig.yaml" apply -f ../kubernetes/load-balancer.yaml
kubectl --kubeconfig="../config/kubeconfig.yaml" get services