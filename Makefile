server:
	npm run dev && open http://localhost:8000/

build:
	npm run build

pull:
	git pull

ship: pull
	git push origin master