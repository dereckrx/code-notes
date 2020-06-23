## Features

- Notes can:
  - be written using Markdown (`.md`) or [MDX](https://mdxjs.com/) (`.mdx`)
  - have zero, one or many tags. See an example [here](https://code-notes-example.netlify.app/syntax-highlighting)
  - have associated emojis 👏
  - be nested in subfolders so you can organise them how you like
- Extra markdown features have also been added. Find out more [here](https://code-notes-example.netlify.app/markdown-features)
- Note search powered by the super-fast [Flexsearch](https://github.com/nextapps-de/flexsearch)

#### Step 2: Develop & Build

Once installed or cloned locally and all packages are installed you can begin developing your site.

```sh
# Run localhost
make dev

# Build your Gatsby site
make build
```

### Note frontmatter

Frontmatter information (written in YAML) can be used to add metadata and extra information for your notes

Only the `title` field is required, the rest are optional.

```yaml
---
title: Note metadata
emoji: 😃
tags:
  - metadata
  - info
link: https://zander.wtf
---

```

#### Link

The `link` item is used to display a link that is related to the note itself. It will appear below the title if.

#### Emoji

The `emoji` frontmatter item will add an emoji beside the title on listing views and above the title on individual note pages

#### Tags

The `tags` array frontmatter item allows you to add as many tags to a note as you'd like.

### Advanced usage

#### PWA

Turn your code notes into a PWA using [this extra config](https://github.com/mrmartineau/notes.zander.wtf/blob/master/gatsby-config.js#L20-L38). This requires `gatsby-plugin-manifest` and `gatsby-plugin-offline`.

```js
// gatsby-config.js
{
  resolve: `gatsby-plugin-manifest`,
  options: {
    name: `Zander's Code Notes`,
    short_name: `CodeNotes`,
    description: `Notes on code. My memory bank.`,
    start_url: `/`,
    background_color: `hsl(210, 38%, 95%)`,
    theme_color: `hsl(345, 100%, 69%)`,
    display: `standalone`,
    icon: `static/logo.png`,
  },
},
{
  resolve: `gatsby-plugin-offline`,
  options: {
    precachePages: [`/*`, `/tag/*`],
  },
},
```

---

## License

[MIT](https://choosealicense.com/licenses/mit/) © [Zander Martineau](https://zander.wtf)

> Made by Zander • [zander.wtf](https://zander.wtf) • [GitHub](https://github.com/mrmartineau/) • [Twitter](https://twitter.com/mrmartineau/)