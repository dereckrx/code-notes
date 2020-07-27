server:
	npm run dev && open http://localhost:8000/

build:
	npm run build

pull:
	git pull

ship: pull
	git push origin master

save: pull
	git add code
	git add notes
	git commit -m "Save notes"
	git push origin master
