xquery version "3.0";

declare variable $doc external;
declare variable $fromVerse external;
declare variable $toVerse external;
declare variable $chapterNumber external;

<quran>
<sura index="{$chapterNumber}" name="{$doc/quran/sura[@index=$chapterNumber]/@name}">
{
for $verse in $doc/quran/sura[@index=$chapterNumber]/aya
where $verse[@index >= $fromVerse and @index <= $toVerse]
return $verse
}
</sura>
</quran>