module.exports = {
  siteMetadata: {
    title: "code-notes",
    description: `Code Notes`,
    author: "Dereck Rasmussen",
  },
  plugins: [
    {
      resolve: "gatsby-theme-code-notes",
      options: {
        contentPath: "notes",
        gitRepoContentPath: "https://github.com/dkrxcode/code-notes/notes",
        basePath: "/",
        showThemeInfo: true,
        showDescriptionInSidebar: true,
      },
    },
  ],
};
