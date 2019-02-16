kubectl --kubeconfig="./.circleci/blade-express-cluster-kubeconfig.yaml" apply -f ./.circleci/frontend-service.yaml
kubectl --kubeconfig="./.circleci/blade-express-cluster-kubeconfig.yaml" apply -f ./.circleci/mail-service.yaml
kubectl --kubeconfig="./.circleci/blade-express-cluster-kubeconfig.yaml" apply -f ./.circleci/ingress.yaml
kubectl --kubeconfig="./.circleci/blade-express-cluster-kubeconfig.yaml" get services