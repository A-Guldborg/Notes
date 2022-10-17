// @ts-nocheck
// Note: type annotations allow type checking and IDEs autocompletion

const lightCodeTheme = require('prism-react-renderer/themes/github');
const darkCodeTheme = require('prism-react-renderer/themes/dracula');

const math = require('remark-math');
const katex = require('rehype-katex');

/** @type {import('@docusaurus/types').Config} */
async function config() {
  const mdxMermaid = await import('mdx-mermaid')

  return {
    title: 'ANH Notes',
    tagline: 'Dinosaurs are cool',
    url: 'https://github.com/A-Guldborg/',
    baseUrl: '/',
    onBrokenLinks: 'throw',
    onBrokenMarkdownLinks: 'warn',
    favicon: 'img/favicon.ico',
    organizationName: 'A-Guldborg', // Usually your GitHub org/user name.
    projectName: 'Notes', // Usually your repo name.

    // Even if you don't use internalization, you can use this field to set useful
    // metadata like html lang. For example, if your site is Chinese, you may want
    // to replace "en" with "zh-Hans".
    i18n: {
      defaultLocale: 'en',
      locales: ['en'],
    },

    presets: [
      [
        'classic',
        /** @type {import('@docusaurus/preset-classic').Options} */
        ({
          docs: {
            sidebarPath: require.resolve('./sidebars.js'),

            remarkPlugins: [math, mdxMermaid.default],
            rehypePlugins: [katex],
            // Please change this to your repo.
            // Remove this to remove the "edit this page" links.
            editUrl:
              'https://github.com/A-Guldborg/Notes/tree/main/packages/create-docusaurus/templates/shared/',
          },
          blog: {
            showReadingTime: true,
            // Please change this to your repo.
            // Remove this to remove the "edit this page" links.
            editUrl:
              'https://github.com/A-Guldborg/Notes/tree/main/packages/create-docusaurus/templates/shared/',
          },
          theme: {
            customCss: require.resolve('./src/css/custom.css'),
          },
        }),
      ],
    ],

    stylesheets: [
      {
        href: 'https://cdn.jsdelivr.net/npm/katex@0.13.24/dist/katex.min.css',
        type: 'text/css',
        integrity:
          'sha384-odtC+0UGzzFL/6PNoE8rX/SPcQDXBJ+uRepguP4QkPCm2LBxH3FA3y+fKSiJ+AmM',
        crossorigin: 'anonymous',
      },
    ],

    themeConfig:
      /** @type {import('@docusaurus/preset-classic').ThemeConfig} */
      ({
        navbar: {
          title: 'Notes',
          logo: {
            alt: 'My Site Logo',
            src: 'img/logo.svg',
          },
          items: [
            {
              type: 'doc',
              docId: 'SEM_01/README',
              position: 'left',
              label: '1st semester',
            },
            {
              type: 'doc',
              docId: 'SEM_02/README',
              position: 'left',
              label: '2nd semester',
            },
            {
              type: 'doc',
              docId: 'SEM_03/README',
              position: 'left',
              label: '3rd semester',
            },
            {
              type: 'doc',
              docId: 'SEM_04/README',
              position: 'left',
              label: '4th semester',
            },
            {
              type: 'doc',
              docId: 'SEM_05/README',
              position: 'left',
              label: '5th semester',
            },
            {
              type: 'doc',
              docId: 'SEM_06/README',
              position: 'left',
              label: '6th semester',
            },
            {to: '/blog', label: 'Blog', position: 'left'},
            {
              href: 'https://github.com/A-Guldborg/Notes',
              label: 'GitHub',
              position: 'right',
            },
          ],
        },
        footer: {
          style: 'dark',
          links: [
            {
              title: 'Docs',
              items: [
                {
                  label: 'Tutorial',
                  to: '/docs/intro',
                },
              ],
            },
            {
              title: 'Community',
              items: [
                {
                  label: 'Stack Overflow',
                  href: 'https://stackoverflow.com/questions/tagged/docusaurus',
                },
                {
                  label: 'Discord',
                  href: 'https://discordapp.com/invite/docusaurus',
                },
                {
                  label: 'Twitter',
                  href: 'https://twitter.com/docusaurus',
                },
              ],
            },
            {
              title: 'More',
              items: [
                {
                  label: 'Blog',
                  to: '/blog',
                },
                {
                  label: 'GitHub',
                  href: 'https://github.com/A-Guldborg/Notes',
                },
              ],
            },
          ],
          copyright: `Copyright © ${new Date().getFullYear()} Notes - Andreas Guldborg Hansen, Inc. Built with Docusaurus.`,
        },
        prism: {
          theme: lightCodeTheme,
          darkTheme: darkCodeTheme,
        },
      }),
    }
  };

module.exports = config;
