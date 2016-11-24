xquery version "3.0";

declare variable $doc external;

<quran>
{
for $chapter in $doc/quran/suras/sura
return <sura index="{$chapter/@index}" name="{$chapter/@name}" ayas="{$chapter/@ayas}"/>
}</quran>