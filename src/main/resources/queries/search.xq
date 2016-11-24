xquery version "3.0";

declare variable $doc external;
declare variable $searchString external;

<quran>
{
for $chapter in $doc/quran/sura
let $chapterNumber := $chapter/@index
return <sura index="{$chapterNumber}" name="{$chapter/@name}">
{
for $verse in $doc/quran/sura[@index=$chapterNumber]/aya[contains(@text, $searchString)]
return $verse
}
</sura>
}
</quran>