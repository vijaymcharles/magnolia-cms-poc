[#if content.textcontent?has_content]
<p>
    ${cmsfn.decode(content).textcontent}
    [#if content.citedPerson?has_content][/#if]
</p>
[/#if]