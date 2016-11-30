xquery version "3.0";

declare variable $doc external;
declare variable $tdoc external;
declare variable $fromVerse external;
declare variable $toVerse external;
declare variable $chapterNumber external;

<quran>
<sura index="{$chapterNumber}" name="{$doc/quran/sura[@index=$chapterNumber]/@name}">
{
for $verse in $doc/quran/sura[@index=$chapterNumber]/aya
where $verse[@index >= $fromVerse and @index <= $toVerse]
return
    <aya index="{$verse/@index}" text="{$verse/@text}" translation="{$tdoc/quran/sura[@index=$chapterNumber]/aya[@index=$verse/@index]/@text}"/>
}
</sura>
</quran>