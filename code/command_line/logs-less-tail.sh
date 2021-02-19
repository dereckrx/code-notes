tail -F /var/log/service-app.log

# Tail and grep continous log (follow)
tail -f *.log | grep --line-buffered -i "error"
