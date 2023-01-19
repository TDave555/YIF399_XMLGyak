<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">

    <html>
        <body>
            <h1>Students</h1>
            <xsl:apply-templates/>
        </body>
    </html>

</xsl:template>



<xsl:template match="class/student">
    <xsl:apply-templates select="keresztnev"/>  
    <xsl:apply-templates select="vezeteknev"/>
    <xsl:apply-templates select="becenev"/>
    <xsl:apply-templates select="kor"/>
    <xsl:apply-templates select="osztondij"/>
</xsl:template>

<xsl:template match="keresztnev">
    Keresztnev: <span style="color:blue"><xsl:value-of select="."/></span><br />
</xsl:template>
<xsl:template match="vezeteknev">
    Vezeteknev: <span style="color:blue"><xsl:value-of select="."/></span><br />
</xsl:template>
<xsl:template match="becenev">
    Becenev: <span style="color:blue"><xsl:value-of select="."/></span><br />
</xsl:template>
<xsl:template match="kor">
    Kor: <span style="color:blue"><xsl:value-of select="."/></span><br />
</xsl:template>
<xsl:template match="osztondij">
    Osztondij: <span style="color:blue"><xsl:value-of select="."/></span><br /><br />
</xsl:template>

</xsl:stylesheet> 