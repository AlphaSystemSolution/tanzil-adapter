xquery version "3.0";

declare variable $doc external;
declare variable $tdoc external;
declare variable $searchString external;

<quran>
{
for $chapter in $doc/quran/sura
let $chapterNumber := $chapter/@index
return <sura index="{$chapterNumber}" name="{$chapter/@name}">
{
for $verse in $doc/quran/sura[@index=$chapterNumber]/aya
where $verse[contains(@text, $searchString)]
return
     <aya index="{$verse/@index}" text="{$verse/@text}" translation="{$tdoc/quran/sura[@index=$chapterNumber]/aya[@index=$verse/@index]/@text}"/>
}
</sura>
}
</quran>