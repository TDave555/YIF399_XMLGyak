<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h1>Autótípusok és darabszámaik csökennő sorrendben:</h1>
        <ul>
          <xsl:for-each-group select="autok/auto" group-by="tipus">
          <xsl:sort select="count(current-group())" order="descending" />
            <li>
              <xsl:value-of select="current-grouping-key()" /> 
              - 
              <xsl:value-of select="count(current-group())" /> darab
            </li>
          </xsl:for-each-group>
        </ul>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>