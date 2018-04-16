import xml.etree.ElementTree as etree

tree = etree.parse('simplePicture.xml')
root = tree.getroot()
for child in root:
   print(child.tag, child.attrib)
print('~~~~~~~~~~~~~~~~~~~~~~~')
ns = {'aa': 'http://schemas.openxmlformats.org/drawingml/2006/main',
      'rr': 'http://schemas.openxmlformats.org/officeDocument/2006/relationships',
      'pp': 'http://schemas.openxmlformats.org/presentationml/2006/main',
      'pp14': 'http://schemas.microsoft.com/office/powerpoint/2010/main'}

for sld in root.findall('pp:sld', ns):
    print(sld.find('p:cNvPr', ns).attrib)
