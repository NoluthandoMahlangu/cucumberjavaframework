docker build -t longlost/cukes-template .
docker run longlost/cukes-template
docker cp 'docker ps -l -q':/app/docker-html-report docker-html-report.html