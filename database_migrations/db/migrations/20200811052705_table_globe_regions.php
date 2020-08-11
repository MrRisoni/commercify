<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TableGlobeRegions extends AbstractMigration
{

    public function change(): void
    {
        $globeRegions = $this->table('globe_regions', ['signed' => false]);
        $globeRegions->addColumn('country_code', 'string', ['limit' => 2])
            ->addColumn('title', 'string', ['limit' => 120])
            ->create();
    }
}
