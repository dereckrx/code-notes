---
title: Code Notes
---

## The Prolblem

I want a way to record code notes or ideas.

* Low risk login: I want to feel ok leaving this account logged into home, work, and co-workers computers. I don't want to log in with my personal google account.
* Data is safe: Data is backed up by a trust company so I don't have to worry about data loss
* Minimal maintainence: Don't want to self host
* Edit anywhere: Can edit notes in the browser and locally
* Optimized code: Markdown support or an editor optimized for code blocks

## The Solution



https://github.com/robertkrimen/gist-it

https://gist-it.appspot.com/

https://blog.revathskumar.com/2012/08/embed-files-from-github-repository-into-webpage.html

## Gist-it

Supply a slice parameter to show only a particular portion of the file:

slice=0:-2	Show the first line up to and including the second to last line
slice=24:100	Show lines 24 through 100
slice=0	Show only the first line of the file

<script src="http://gist-it.appspot.com/https://github.com/dereckrx/perform/blob/master/Makefile?slice=9:19">
</script>

https://github.com/dereckrx/perform/blob/59975018cd8324060d1d8e1792883ebf95faa3fb/Makefile#L9
