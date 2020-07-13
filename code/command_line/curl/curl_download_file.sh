# This is equivelent to wget
# -O, --remote-name    Use end of the url as filename
# -o    Specify a file name: -o image.jpg
# -L    Follow redirects

curl -OL https://images.unsplash.com/photo-1506812574058-fc75fa93fead
ls -hal
rm photo-1506812574058-fc75fa93fead
