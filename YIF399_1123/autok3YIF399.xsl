<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
  xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <body>
        <h1>Az elemek száma:</h1>
        <p>
          <xsl:value-of select="count(/autok/auto)" />
        </p>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>