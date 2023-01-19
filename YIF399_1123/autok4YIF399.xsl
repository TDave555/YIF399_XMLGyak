<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h1>Miskolci tulajdonosok autóinak rendszámai:</h1>
        <ul>
          <xsl:for-each select="autok/auto">

            <xsl:if test="tulaj/varos = 'Miskolc'">
              <li>
                <xsl:value-of select="@rsz" />
              </li>
            </xsl:if>
          </xsl:for-each>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>