npm install http-server -g
http-server ./ -p 1313 


## Nginx 

nginx -c /path/to/config.conf

Config:
```
http {

    server {
        listen       8000;
        server_name  localhost;

        location /login {
            proxy_pass http://localhost:8080/login;
        }

        location /logout {
            proxy_pass http://localhost:8080/logout;
        }

        location /data {
            proxy_pass http://localhost:8080/data;
        }

        location / {
            proxy_pass http://localhost:3000/;
        }
    }
}
```
