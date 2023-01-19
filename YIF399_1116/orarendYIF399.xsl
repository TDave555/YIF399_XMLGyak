<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/" > 
<html>
    <body>
        <h1>TDO Orarend - 2022/23. I. felev.</h1>
        <table border="2">
            <tr>
                <td>id</td>
                <td>tipus</td>
                <td>targy</td>
                <td>nap</td>
                <td>tol</td>
                <td>ig</td>
                <td>helyszin</td>
                <td>oktato</td>
                <td>szak</td>
            </tr>
            <xsl:for-each select="toth_david_orarend/ora">
                <tr>
                    <td> <xsl:value-of select="@id"/></td>
                    <td> <xsl:value-of select="@tipus"/></td>
                    <td> <xsl:value-of select="targy"/></td>
                    <td> <xsl:value-of select="idopont/nap"/></td>
                    <td> <xsl:value-of select="idopont/tol"/></td>
                    <td> <xsl:value-of select="idopont/ig"/></td>
                    <td> <xsl:value-of select="helyszin"/></td>
                    <td> <xsl:value-of select="oktato"/></td>
                    <td> <xsl:value-of select="szak"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </body>
</html>
</xsl:template>
</xsl:stylesheet>