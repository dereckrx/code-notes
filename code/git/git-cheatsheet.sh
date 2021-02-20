# Rebase development into foo
git rebase master feature/foo

# Remove last commit
git reset HEAD~1

## Squash and Rebase:
# * Only rebase/squash code that has not been pushed and/or shared
# 1. Squash related commits before CR or merge (combine)
# 2. Rebase develop into feature (move changes to-top)
# 3. Merge features into develop/master (fast forward, no merge commit)

# Squash/Combine several commits into one:
git rebase -i HEAD~3
git rebase -i HEAD~<num_of_commits_to_squash>

# In the text editor that comes up,
# replace the words "pick" with "squash" next to the commits you want to squash
# into the commit before it.
# Move the "pick" or "reword" commit to the top.

## Diff
git show <commit> // See changes in commit

