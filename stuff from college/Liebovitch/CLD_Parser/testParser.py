from lxml import etree

tree = etree.parse('xml_text.xml')
root = tree.getroot()
for a in root.findall('actor', root.nsmap):
    print(a)
